CREATE TABLE IF NOT EXISTS hotels (
    hotel_id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS bookings (
    booking_id UUID PRIMARY KEY,
    check_in_date TIMESTAMP NOT NULL,
    check_out_date TIMESTAMP NOT NULL,
    book_date TIMESTAMP NOT NULL,
    phone_number VARCHAR(20),
    name VARCHAR(255),
    number_of_people INT,
    hotel_id UUID NOT NULL,
    CONSTRAINT fk_booking_hotel FOREIGN KEY (hotel_id) REFERENCES hotels(hotel_id)
);
