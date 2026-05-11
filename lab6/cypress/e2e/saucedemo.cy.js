describe('Zadanie 6: 20 Testów Funkcjonalnych Sauce Demo', () => {

  beforeEach(() => {

    cy.visit('/');
  });

  // logowanie

  it('1. Logowanie: Poprawne dane', () => {
    cy.get('[data-test="username"]').type('standard_user');
    cy.get('[data-test="password"]').type('secret_sauce');
    cy.get('[data-test="login-button"]').click();
    cy.url().should('include', '/inventory.html');
    cy.get('.title').should('have.text', 'Products');
  });

  it('2. Logowanie: Zablokowany użytkownik', () => {
    cy.get('[data-test="username"]').type('locked_out_user');
    cy.get('[data-test="password"]').type('secret_sauce');
    cy.get('[data-test="login-button"]').click();
    cy.get('[data-test="error"]').should('contain', 'Sorry, this user has been locked out.');
  });

  it('3. Logowanie: Błędne hasło', () => {
    cy.get('[data-test="username"]').type('standard_user');
    cy.get('[data-test="password"]').type('wrong_password');
    cy.get('[data-test="login-button"]').click();
    cy.get('[data-test="error"]').should('be.visible');
  });

  it('4. Logowanie: Brak nazwy użytkownika', () => {
    cy.get('[data-test="password"]').type('secret_sauce');
    cy.get('[data-test="login-button"]').click();
    cy.get('[data-test="error"]').should('contain', 'Username is required');
  });

  it('5. Logowanie: Brak hasła', () => {
    cy.get('[data-test="username"]').type('standard_user');
    cy.get('[data-test="login-button"]').click();
    cy.get('[data-test="error"]').should('contain', 'Password is required');
  });

  // produty i sortowanie

  describe('Testy produktowe', () => {
    beforeEach(() => {
      // Logowanie przed testami produktowymi
      cy.get('[data-test="username"]').type('standard_user');
      cy.get('[data-test="password"]').type('secret_sauce');
      cy.get('[data-test="login-button"]').click();
    });

    it('6. Widoczność listy produktów', () => {
      cy.get('.inventory_item').should('have.length.at.least', 1);
    });

    it('7. Sortowanie: Nazwa (Z do A)', () => {
      cy.get('[data-test="product-sort-container"]').select('za');
      cy.get('.inventory_item_name').first().should('have.text', 'Test.allTheThings() T-Shirt (Red)');
    });

    it('8. Sortowanie: Cena (od najniższej)', () => {
      cy.get('[data-test="product-sort-container"]').select('lohi');
      cy.get('.inventory_item_price').first().should('have.text', '$7.99');
    });

    it('9. Sortowanie: Cena (od najwyższej)', () => {
      cy.get('[data-test="product-sort-container"]').select('hilo');
      cy.get('.inventory_item_price').first().should('have.text', '$49.99');
    });

    it('10. Otwarcie szczegółów produktu', () => {
      cy.get('.inventory_item_name').first().click();
      cy.get('[data-test="back-to-products"]').should('be.visible');
      cy.url().should('include', '/inventory-item.html');
    });

    // koszyk

    it('11. Dodanie produktu do koszyka (Backpack)', () => {
      cy.get('[data-test="add-to-cart-sauce-labs-backpack"]').click();
      cy.get('.shopping_cart_badge').should('have.text', '1');
    });

    it('12. Dodanie wielu produktów', () => {
      cy.get('[data-test="add-to-cart-sauce-labs-bike-light"]').click();
      cy.get('[data-test="add-to-cart-sauce-labs-bolt-t-shirt"]').click();
      cy.get('.shopping_cart_badge').should('have.text', '2');
    });

    it('13. Usunięcie produktu z poziomu strony głównej', () => {
      cy.get('[data-test="add-to-cart-sauce-labs-backpack"]').click();
      cy.get('[data-test="remove-sauce-labs-backpack"]').click();
      cy.get('.shopping_cart_badge').should('not.exist');
    });

    it('14. Przejście do widoku koszyka', () => {
      cy.get('.shopping_cart_link').click();
      cy.url().should('include', '/cart.html');
      cy.get('.title').should('have.text', 'Your Cart');
    });

    it('15. Usunięcie produktu będąc w koszyku', () => {
      cy.get('[data-test="add-to-cart-sauce-labs-backpack"]').click();
      cy.get('.shopping_cart_link').click();
      cy.get('[data-test="remove-sauce-labs-backpack"]').click();
      cy.get('.cart_item').should('not.exist');
    });

    it('16. Kontynuacja zakupów z koszyka', () => {
      cy.get('.shopping_cart_link').click();
      cy.get('[data-test="continue-shopping"]').click();
      cy.url().should('include', '/inventory.html');
    });

    // checkout menu

    it('17. Przejście do Checkout', () => {
      cy.get('[data-test="add-to-cart-sauce-labs-backpack"]').click();
      cy.get('.shopping_cart_link').click();
      cy.get('[data-test="checkout"]').click();
      cy.get('.title').should('have.text', 'Checkout: Your Information');
    });

    it('18. Walidacja pustego formularza Checkout', () => {
      cy.get('.shopping_cart_link').click();
      cy.get('[data-test="checkout"]').click();
      cy.get('[data-test="continue"]').click();
      cy.get('[data-test="error"]').should('contain', 'Error: First Name is required');
    });

    it('19. Pełny proces zakupu (Happy Path)', () => {
      cy.get('[data-test="add-to-cart-sauce-labs-backpack"]').click();
      cy.get('.shopping_cart_link').click();
      cy.get('[data-test="checkout"]').click();
      cy.get('[data-test="firstName"]').type('Jan');
      cy.get('[data-test="lastName"]').type('Kowalski');
      cy.get('[data-test="postalCode"]').type('00-001');
      cy.get('[data-test="continue"]').click();
      cy.get('[data-test="finish"]').click();
      cy.get('.complete-header').should('have.text', 'Thank you for your order!');
    });

    it('20. Wylogowanie', () => {
      cy.get('#react-burger-menu-btn').click();
      cy.get('#logout_sidebar_link').click();
      cy.get('[data-test="login-button"]').should('be.visible');
    });
  });
});