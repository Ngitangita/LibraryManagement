	
	CREATE TABLE IF NOT EXISTS  "topic" (
	    id SERIAL PRIMARY KEY,
	    book_id INT REFERENCES "book"(id),
	    topic VARCHAR(50) CHECK(topic IN ('ROMANCE', 'COMEDY', 'OTHER')) NOT NULL
	);