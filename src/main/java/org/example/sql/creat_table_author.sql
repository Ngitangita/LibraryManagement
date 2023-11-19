	

	CREATE TABLE IF NOT EXISTS  "author" (
	    id SERIAL PRIMARY KEY,
	    name VARCHAR(255) NOT NULL,
	    sex CHAR(1) CHECK(sex IN ('M', 'F')) NOT NULL
	);
