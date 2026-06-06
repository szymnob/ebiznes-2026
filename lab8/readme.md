#Szymon Biel

https://github.com/user-attachments/assets/7bb53015-1c57-49ef-b931-d46767671743

Należy skonfigurować klienta Oauth2 (4.0). Dane o użytkowniku wraz z
tokenem powinny być przechowywane po stronie bazy serwera, a nowy
token (inny niż ten od dostawcy) powinien zostać wysłany do klienta
(React). Można zastosować mechanizm sesji lub inny dowolny (5.0).
Zabronione jest tworzenie klientów bezpośrednio po stronie React'a
wyłączając z komunikacji aplikację serwerową.

Prawidłowa komunikacja: react-sewer-dostawca-serwer(via return
uri)-react.

✅ 3.0 logowanie przez aplikację serwerową (bez Oauth2)
[commit](https://github.com/szymnob/ebiznes-2026/commit/dce96a18a0399b14542658234de70f572adb4150)

✅ 3.5 rejestracja przez aplikację serwerową (bez Oauth2)
[commit](https://github.com/szymnob/ebiznes-2026/commit/ce5e8ac337694ab077c5cf3a44da2abe893936dd)

4.0 logowanie via Google OAuth2

4.5 logowanie via Facebook lub Github OAuth2

5.0 zapisywanie danych logowania OAuth2 po stronie serwera
