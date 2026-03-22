import java.sql.DriverManager

fun main() {
    println("--- Hello World z Kotlina w Dockerze! ---")
    
    // Test JDBC SQLite
    try {
        val conn = DriverManager.getConnection("jdbc:sqlite::memory:")
        println("Sukces: Połączono z bazą SQLite (w pamięci)!")
        conn.close()
    } catch (e: Exception) {
        println("Błąd JDBC: ${e.message}")
    }
}