
Należy stworzyć projekt w echo w Go. Należy wykorzystać gorm do
stworzenia kilka modeli, gdzie pomiędzy dwoma musi być relacja. Należy
zaimplementować proste endpointy do dodawania oraz wyświetlania danych
za pomocą modeli. Jako bazę danych można wybrać dowolną, sugerowałbym
jednak pozostać przy sqlite.

✅ 3.0 Należy stworzyć aplikację we frameworki echo w j. Go, która będzie
miała kontroler Produktów zgodny z CRUD
[commit](https://github.com/szymnob/ebiznes-2026/commit/066f0ef9a293db704bdce4b8127fe78facc93a33)

✅ 3.5 Należy stworzyć model Produktów wykorzystując gorm oraz
wykorzystać model do obsługi produktów (CRUD) w kontrolerze (zamiast
listy)
[commit](https://github.com/szymnob/ebiznes-2026/commit/6d05880ebc6f0ef7f07989d2b4ac36ed52ca976a)

❌ 4.0 Należy dodać model Koszyka oraz dodać odpowiedni endpoint

❌ 4.5 Należy stworzyć model kategorii i dodać relację między kategorią,
a produktem

❌ 5.0 pogrupować zapytania w gorm’owe scope'y