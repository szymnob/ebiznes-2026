package main

import (
	"net/http"
	"strconv"

	"github.com/labstack/echo/v4"
)

type Product struct {
	ID    int     `json:"id"`
	Name  string  `json:"name"`
	Price float64 `json:"price"`
}

var products = []Product{
	{ID: 1, Name: "Laptop", Price: 3500.00},
}
var nextID = 2

func main() {
	e := echo.New()


	// create
	e.POST("/products", func(c echo.Context) error {
		p := new(Product)
		if err := c.Bind(p); err != nil {
			return err
		}
		p.ID = nextID
		nextID++
		products = append(products, *p)
		return c.JSON(http.StatusCreated, p)
	})

	// readd all
	e.GET("/products", func(c echo.Context) error {
		return c.JSON(http.StatusOK, products)
	})

	// read by id
	e.GET("/products/:id", func(c echo.Context) error {
		id, _ := strconv.Atoi(c.Param("id"))
		for _, p := range products {
			if p.ID == id {
				return c.JSON(http.StatusOK, p)
			}
		}
		return c.JSON(http.StatusNotFound, "Nie ma takiego produktu")
	})

	// update
	e.PUT("/products/:id", func(c echo.Context) error {
		id, _ := strconv.Atoi(c.Param("id"))
		for i, p := range products {
			if p.ID == id {
				c.Bind(&products[i])
				products[i].ID = id // Upewniamy się, że ID się nie zmieniło
				return c.JSON(http.StatusOK, products[i])
			}
		}
		return c.JSON(http.StatusNotFound, "Nie znaleziono")
	})

	// delete
	e.DELETE("/products/:id", func(c echo.Context) error {
		id, _ := strconv.Atoi(c.Param("id"))
		for i, p := range products {
			if p.ID == id {
				products = append(products[:i], products[i+1:]...)
				return c.NoContent(http.StatusNoContent)
			}
		}
		return c.JSON(http.StatusNotFound, "Nie znaleziono")
	})

	e.Logger.Fatal(e.Start(":1323"))
}