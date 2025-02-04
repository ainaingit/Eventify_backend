# Eventify - Event Management Application

Eventify is a comprehensive event management platform designed to help users create, manage, and attend events seamlessly. Whether you're organizing a conference, concert, wedding, or a casual meet-up, Eventify has the tools you need to keep everything organized.

## Features

### For Event Organizers:
- **Event Creation**: Create events with details such as date, time, location, description, and event type (e.g., conference, concert, wedding).
- **Event Management**: Edit and delete events, manage attendee lists, track participation, and update event details as necessary.
- **Payment Integration**: Accept online payments for paid events, manage ticket sales, and generate tickets.
- **Notifications**: Send automated reminders, updates, and notifications to attendees about event changes or important announcements.
- **Dashboard**: Access a user-friendly dashboard to manage multiple events, track registrations, and view event performance metrics.
- **Participant List**: View a list of registered participants, including their contact information, and track their attendance.
- **Event Analytics**: Detailed reports on ticket sales, participant numbers, event feedback, and more.

### For Event Attendees:
- **Event Registration**: Easily register for events and receive instant confirmations via email or push notifications.
- **Event Calendar**: View upcoming events in a calendar format, filter by date, location, or event type.
- **Tickets**: Generate digital tickets with QR codes for easy access to events. Tickets can be stored on mobile devices for quick entry.
- **Event Sharing**: Share events with friends, family, and colleagues on social media platforms such as Facebook, Twitter, and Instagram.
- **Feedback & Reviews**: After attending an event, leave feedback and ratings to help future attendees.

### General Features:
- **Search & Filters**: Advanced search functionality to find events by keyword, location, date, and event type.
- **Event Reminders**: Automated email and push notifications to remind participants about upcoming events.
- **Multilingual Support**: The platform supports multiple languages, making it accessible to a global audience.
- **QR Code Check-in**: Streamline event check-ins by using QR codes for easy scanning upon entry.
- **Custom Event Types**: Create custom event categories to better organize different types of events.
- **Real-time Updates**: Event details (location, time, etc.) are updated in real-time, and participants are notified immediately.

## Technologies Used

- **Frontend**: React, Bootstrap
- **Backend**: Spring Boot if Java is preferred
- **Database**: PostgreSQL 
- **Payment Gateway**: Stripe / PayPal (if applicable)
- **Authentication**: JWT (JSON Web Token) / OAuth
- **Notifications**: Email service (e.g., SendGrid) or Push Notifications (e.g., Firebase)
- **Hosting/Deployment**: Heroku / DigitalOcean / AWS (depending on preference)

## Installation

### Clone the repository:

```bash
git clone https://github.com/yourusername/eventify.git
cd eventify


# Eventify Setup Guide

## Frontend Setup:

1. Navigate to the frontend directory:
   cd client
   npm install
    npm start

Backend Setup:
Navigate to the backend directory:
    cd server
    Install dependencies:
        npm install
    Start the backend server:
        npm run dev
        ## Backend Setup:

        1. Navigate to the backend directory:
            ```bash
            cd server
            ```

        2. Install dependencies:
            ```bash
            mvn install
            ```

        3. Configure the database:
            - Update the `application.properties` file with your PostgreSQL database credentials.

        4. Start the backend server:
            ```bash
            mvn spring-boot:run
            ```

        5. Access the API documentation (if available):
            - Open your browser and navigate to `http://localhost:8080/swagger-ui.html` to view the API documentation.