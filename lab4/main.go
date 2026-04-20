package main

import (
	"net/http"
	"lab4/database"
	"lab4/models" 
	"github.com/labstack/echo/v4"
)

func main() {
	database.InitDB()

	e := echo.New()

	// get all
	e.GET("/products", func(c echo.Context) error {
		var products []models.Product
		database.DB.Find(&products)
		return c.JSON(http.StatusOK, products)
	})

	// POST
	e.POST("/products", func(c echo.Context) error {
		p := new(models.Product)
		if err := c.Bind(p); err != nil {
			return err
		}
		database.DB.Create(&p)
		return c.JSON(http.StatusCreated, p)
	})

	// Get id
	e.GET("/products/:id", func(c echo.Context) error {
		id := c.Param("id")
		var product models.Product
		if err := database.DB.First(&product, id).Error; err != nil {
			return c.JSON(http.StatusNotFound, "Brak produktu")
		}
		return c.JSON(http.StatusOK, product)
	})

	// PUT
	e.PUT("/products/:id", func(c echo.Context) error {
		id := c.Param("id")
		var product models.Product
		if err := database.DB.First(&product, id).Error; err != nil {
			return c.JSON(http.StatusNotFound, "Nie ma co aktualizować")
		}
		c.Bind(&product)
		database.DB.Save(&product)
		return c.JSON(http.StatusOK, product)
	})

	// dalete
	e.DELETE("/products/:id", func(c echo.Context) error {
		id := c.Param("id")
		database.DB.Delete(&models.Product{}, id)
		return c.NoContent(http.StatusNoContent)
	})

	e.Logger.Fatal(e.Start(":1323"))
}