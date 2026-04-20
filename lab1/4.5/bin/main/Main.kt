import java.sql.DriverManager

fun main() {
    println("Hello world")
    
    // Test JDBC SQLite
    try {
        val conn = DriverManager.getConnection("jdbc:sqlite::memory:")
        println("Polaczono z baza")
        conn.close()
    } catch (e: Exception) {
        println("Błąd JDBC: ${e.message}")
    }
}