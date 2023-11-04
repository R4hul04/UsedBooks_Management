
Replace `<host>` and `<port>` with the appropriate values for your deployment.

## Endpoints and Operations

### Retrieve All Books

- **Endpoint:** `GET /books`
- **Functionality:** Retrieves a list of all books available in the inventory.

### Get Book by ID

- **Endpoint:** `GET /books/{id}`
- **Functionality:** Retrieves a single book by its unique identifier.
- **Parameters:**
  - `id`: The ID of the book to retrieve.

### Create a New Book

- **Endpoint:** `POST /books`
- **Functionality:** Creates a new book entry in the inventory.
- **Payload:** JSON object representing the book.
  - Example: `{ "isbn": "123456789", "title": "The Great Gatsby", "authors": "F. Scott Fitzgerald", ... }`

### Delete a Book

- **Endpoint:** `POST /books/delete/{id}`
- **Functionality:** Deletes a book from the inventory by its ID.
- **Parameters:**
  - `id`: The ID of the book to delete.

### Update a Book

- **Endpoint:** `POST /books/update/{id}`
- **Functionality:** Updates the details of an existing book.
- **Parameters:**
  - `id`: The ID of the book to update.
- **Payload:** JSON object with updated book data.

### Buy a Book

- **Endpoint:** `POST /books/buy`
- **Functionality:** Processes the purchase of a book by its ID.
- **Payload:** JSON object containing the ID of the book to purchase.
  - Example: `{ "id": 1 }`

### Sell a Book

- **Endpoint:** `POST /books/sell`
- **Functionality:** Processes the selling of a book by its ID or adds a new book to the inventory if ID is not provided.
- **Payload:** JSON object containing the ID of the book to sell, or book details to add to the inventory.
  - Example: `{ "id": 2 }` or `{ "isbn": "123456789", "title": "New Book", ... }`

### Get Inventory of Books

- **Endpoint:** `GET /books/inventory`
- **Functionality:** Provides a list of books along with their inventory status.

## Error Handling

- All endpoints will return appropriate HTTP status codes for success and error cases.
- Error messages will be provided in the response body for client-side handling.

## Examples

Below are some example uses of the API endpoints:

### Retrieving All Books

Request:

