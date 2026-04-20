package database

import (
	"lab4/models" 
	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
)

var DB *gorm.DB

func InitDB() {
	var err error
	DB, err = gorm.Open(sqlite.Open("produkty.db"), &gorm.Config{})
	if err != nil {
		panic("Nie udało się połączyć z bazą danych")
	}

	DB.AutoMigrate(&models.Product{})
}