CREATE TABLE "users" (
  "id" serial PRIMARY KEY,
  "name" varchar(255) NOT NULL,
  "email" varchar(255) UNIQUE NOT NULL,
  "password" varchar(255) NOT NULL,
  "role" varchar(50) NOT NULL
);

CREATE TABLE "event_categories" (
  "id" serial PRIMARY KEY,
  "name" varchar(255) NOT NULL
);

CREATE TABLE "events" (
  "id" serial PRIMARY KEY,
  "title" varchar(255) NOT NULL,
  "description" text,
  "dateTime" timestamp NOT NULL,
  "location" varchar(255),
  "category_id" int,
  "organizer_id" int
);

CREATE TABLE "registrations" (
  "id" serial PRIMARY KEY,
  "registrationDate" timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
  "ticketQRCode" varchar(255),
  "user_id" int,
  "event_id" int
);

CREATE TABLE "payments" (
  "id" serial PRIMARY KEY,
  "amount" decimal(10,2) NOT NULL,
  "paymentDate" timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
  "method" varchar(50),
  "status" varchar(50),
  "registration_id" int
);

CREATE TABLE "feedback" (
  "id" serial PRIMARY KEY,
  "rating" int,
  "comment" text,
  "event_id" int,
  "user_id" int
);

CREATE TABLE "notifications" (
  "id" serial PRIMARY KEY,
  "message" text NOT NULL,
  "sentDate" timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
  "readStatus" boolean NOT NULL DEFAULT false,
  "user_id" int,
  "event_id" int
);

COMMENT ON COLUMN "feedback"."rating" IS 'CHECK (rating >= 0 AND rating <= 5)';

COMMENT ON COLUMN "notifications"."event_id" IS 'nullable';

ALTER TABLE "events" ADD FOREIGN KEY ("category_id") REFERENCES "event_categories" ("id");

ALTER TABLE "events" ADD FOREIGN KEY ("organizer_id") REFERENCES "users" ("id");

ALTER TABLE "registrations" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "registrations" ADD FOREIGN KEY ("event_id") REFERENCES "events" ("id");

ALTER TABLE "payments" ADD FOREIGN KEY ("registration_id") REFERENCES "registrations" ("id");

ALTER TABLE "feedback" ADD FOREIGN KEY ("event_id") REFERENCES "events" ("id");

ALTER TABLE "feedback" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "notifications" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "notifications" ADD FOREIGN KEY ("event_id") REFERENCES "events" ("id");
