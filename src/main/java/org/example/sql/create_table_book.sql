

	CREATE TABLE IF NOT EXISTS  "book" (
	    id SERIAL 	PRIMARY KEY,
	    book_name VARCHAR(255) NOT NULL,
	    page_numbers INT NOT NULL,
	    release_date DATE NOT NULL,
	    author_id INT REFERENCES "author"(id)
	);