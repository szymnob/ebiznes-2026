import os
import httpx
from fastapi import FastAPI, HTTPException
from fastapi.responses import PlainTextResponse  # <-- Nowy import
from pydantic import BaseModel

OLLAMA_URL = os.getenv("OLLAMA_URL", "http://localhost:11434")
OLLAMA_MODEL = os.getenv("OLLAMA_MODEL", "qwen2.5:0.5b") 

app = FastAPI(title="Ollama Proxy Service")

class ChatRequest(BaseModel):
    message: str


@app.get("/health")
async def health():
    return {"status": "ok", "model": OLLAMA_MODEL}

@app.post("/chat", response_class=PlainTextResponse)
async def chat(request: ChatRequest):
    message = request.message.strip()
    
    if not message:
        raise HTTPException(status_code=400, detail="Wiadomość nie może być pusta.")

    async with httpx.AsyncClient() as client:
        try:
            response = await client.post(
                f"{OLLAMA_URL}/api/chat",
                json={
                    "model": OLLAMA_MODEL,
                    "messages": [{"role": "user", "content": message}],
                    "stream": False,
                },
                timeout=120.0
            )
            response.raise_for_status() 
            
            data = response.json()
            
            return data["message"]["content"]
            
        except httpx.RequestError as e:
            raise HTTPException(status_code=502, detail=f"Błąd połączenia z Ollamą: {str(e)}")
        except (KeyError, ValueError) as e:
            raise HTTPException(status_code=502, detail=f"Nieprawidłowa odpowiedź od Ollamy: {str(e)}")

if __name__ == "__main__":
    import uvicorn
    uvicorn.run("main:app", host="0.0.0.0", port=5000, reload=True)