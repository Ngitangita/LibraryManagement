	
	CREATE TABLE IF NOT EXISTS  "borrowing" (
	    id SERIAL PRIMARY KEY,
	    subscriber_id INT REFERENCES "subscriber"(id),
	    book_id INT REFERENCES "book"(id),
	    borrowing_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	    return_date TIMESTAMP,
	    administrator_id INT REFERENCES "administrator"(id)
	    CONSTRAINT check_return_date CHECK (return_date IS NULL OR return_date >= borrowing_date)
	);