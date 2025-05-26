// main.js - Community Event Portal JavaScript Implementation

//  1. JavaScript Basics & Setup
console.log("Welcome to the Community Portal");

window.addEventListener("load", function () {
  alert("Community Portal is fully loaded!");
});

// 2. Syntax, Data Types, and Operators
const EVENT_NAME = "Community Music Festival";
const EVENT_DATE = "2025-06-15";
let availableSeats = 100;

function updateSeatDisplay() {
  const seatDisplay = document.getElementById("seatDisplay");
  if (seatDisplay) {
    seatDisplay.textContent = `Available seats: ${availableSeats}`;
  }
}

function registerForEvent() {
  if (availableSeats > 0) {
    availableSeats--;
    updateSeatDisplay();
    console.log(
      `Registered for ${EVENT_NAME} on ${EVENT_DATE}. Seats left: ${availableSeats}`
    );
  } else {
    console.log("Sorry, no seats available!");
  }
}

//3. Conditionals, Loops, and Error Handling
const events = [
  {
    id: 1,
    name: "Music Festival",
    date: "2025-06-15",
    seats: 50,
    category: "music",
  },
  {
    id: 2,
    name: "Art Workshop",
    date: "2025-05-10",
    seats: 0,
    category: "art",
  },
  {
    id: 3,
    name: "Charity Run",
    date: "2025-07-20",
    seats: 100,
    category: "sports",
  },
  {
    id: 4,
    name: "Tech Conference",
    date: "2025-08-05",
    seats: 30,
    category: "tech",
  },
];

function displayValidEvents() {
  const today = new Date().toISOString().split("T")[0];
  const validEvents = events.filter(
    (event) => event.date >= today && event.seats > 0
  );

  const eventList = document.getElementById("eventList");
  if (eventList) {
    eventList.innerHTML = "";

    validEvents.forEach((event) => {
      try {
        const eventElement = document.createElement("div");
        eventElement.className = "event-item";
        eventElement.innerHTML = `
                    <h3>${event.name}</h3>
                    <p>Date: ${event.date}</p>
                    <p>Seats available: ${event.seats}</p>
                    <button onclick="registerForEventById(${event.id})">Register</button>
                `;
        eventList.appendChild(eventElement);
      } catch (error) {
        console.error(`Error displaying event ${event.id}:`, error);
      }
    });
  }
}

function registerForEventById(eventId) {
  try {
    const event = events.find((e) => e.id === eventId);
    if (event && event.seats > 0) {
      event.seats--;
      displayValidEvents();
      alert(`Successfully registered for ${event.name}!`);
    } else {
      throw new Error("No seats available or event not found");
    }
  } catch (error) {
    console.error("Registration error:", error);
    alert("Registration failed. Please try again.");
  }
}

// 4. Functions, Scope, Closures, Higher-Order Functions
function createEventManager() {
  let totalRegistrations = 0;

  return {
    addEvent: function (newEvent) {
      events.push(newEvent);
      displayValidEvents();
    },
    registerUser: function (eventId) {
      const event = events.find((e) => e.id === eventId);
      if (event && event.seats > 0) {
        event.seats--;
        totalRegistrations++;
        return true;
      }
      return false;
    },
    getTotalRegistrations: function () {
      return totalRegistrations;
    },
    filterEvents: function (callback) {
      return events.filter(callback);
    },
  };
}

const eventManager = createEventManager();

function filterEventsByCategory(category) {
  return eventManager.filterEvents((event) => event.category === category);
}

// 5. Objects and Prototypes
class Event {
  constructor(id, name, date, seats, category) {
    this.id = id;
    this.name = name;
    this.date = date;
    this.seats = seats;
    this.category = category;
  }

  checkAvailability() {
    return this.seats > 0;
  }

  displayInfo() {
    return `${this.name} on ${this.date} (${this.seats} seats available)`;
  }
}

// Example usage:
const newEvent = new Event(5, "Book Fair", "2025-09-10", 80, "literature");
eventManager.addEvent(newEvent);

// Display object entries
console.log("Event object entries:", Object.entries(newEvent));

// 6. Arrays and Methods
function addNewEvent() {
  const newEvent = {
    id: events.length + 1,
    name: "New Community Workshop",
    date: "2025-10-15",
    seats: 40,
    category: "workshop",
  };
  events.push(newEvent);
  displayValidEvents();
}

function showMusicEvents() {
  const musicEvents = events.filter((event) => event.category === "music");
  console.log("Music events:", musicEvents);
}

function formatEventCards() {
  return events.map((event) => ({
    title: `${
      event.category.charAt(0).toUpperCase() + event.category.slice(1)
    }: ${event.name}`,
    date: event.date,
    seats: event.seats,
  }));
}

// 7. DOM Manipulation
function createEventCard(event) {
  const card = document.createElement("div");
  card.className = "event-card";

  const title = document.createElement("h3");
  title.textContent = event.name;

  const date = document.createElement("p");
  date.textContent = `Date: ${event.date}`;

  const seats = document.createElement("p");
  seats.textContent = `Seats available: ${event.seats}`;

  const registerBtn = document.createElement("button");
  registerBtn.textContent = "Register";
  registerBtn.onclick = () => registerForEventById(event.id);

  card.appendChild(title);
  card.appendChild(date);
  card.appendChild(seats);
  card.appendChild(registerBtn);

  return card;
}

function renderEvents(eventArray) {
  const eventList = document.getElementById("eventList");
  if (!eventList) return;

  eventList.innerHTML = "";

  if (eventArray.length === 0) {
    eventList.innerHTML = "<p>No matching events found.</p>";
    return;
  }

  eventArray.forEach((event) => {
    const card = createEventCard(event);
    eventList.appendChild(card);
  });
}

// Combined filter logic for category and search
function applyFilters() {
    const category = document.getElementById("categoryFilter")?.value || "";
    const searchTerm = document.getElementById("eventSearch")?.value.toLowerCase() || "";
  
    const today = new Date();
  
    const filtered = events.filter((event) => {
      const eventDate = new Date(event.date);
      return (
        eventDate >= today &&
        event.seats > 0 &&
        (category === "" || event.category === category) &&
        event.name.toLowerCase().includes(searchTerm)
      );
    });
  
    renderEvents(filtered);
  }


// 8. Event Handling
document.addEventListener("DOMContentLoaded", function () {
  document.getElementById("categoryFilter")?.addEventListener("change", applyFilters);
  document.getElementById("eventSearch")?.addEventListener("input", applyFilters);
});

// 9. Async JS, Promises, Async/Await
async function fetchEvents() {
  const loadingSpinner = document.getElementById("loadingSpinner");
  if (loadingSpinner) loadingSpinner.style.display = "block";

  try {
    // Using a mock API endpoint
    const response = await fetch("https://jsonplaceholder.typicode.com/posts");
    const data = await response.json();

    // Simulate transforming the data to our event format
    const mockEvents = data.slice(0, 4).map((item, index) => ({
      id: index + 100,
      name: item.title.slice(0, 20),
      date: `2025-${(index + 6).toString().padStart(2, "0")}-15`,
      seats: Math.floor(Math.random() * 50) + 10,
      category: ["music", "art", "sports", "tech"][index],
    }));

    mockEvents.forEach((event) => eventManager.addEvent(event));
  } catch (error) {
    console.error("Failed to fetch events:", error);
  } finally {
    if (loadingSpinner) loadingSpinner.style.display = "none";
  }
}

// ======================
// 10. Modern JavaScript Features
// ======================
function addEventWithDefaults({
  name = "New Event",
  date = new Date().toISOString().split("T")[0],
  seats = 50,
  category = "general",
} = {}) {
  const newId = Math.max(...events.map((e) => e.id), 0) + 1;
  const newEvent = { id: newId, name, date, seats, category };
  events.push(newEvent);
  return newEvent;
}

function cloneEventList() {
  return [...events]; // Using spread operator to clone
}

// ======================
// 11. Working with Forms
// ======================
document
  .getElementById("registrationForm")
  ?.addEventListener("submit", function (e) {
    e.preventDefault();

    const form = e.target;
    const name = form.elements["name"].value.trim();
    const email = form.elements["email"].value.trim();
    const eventType = form.elements["eventType"].value;

    // Validation
    if (!name || !email || !eventType) {
      alert("Please fill in all required fields");
      return;
    }

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      alert("Please enter a valid email address");
      return;
    }

    // If validation passes
    alert(
      `Thank you ${name}! You've registered for the ${eventType} event. Confirmation sent to ${email}`
    );
    form.reset();
  });

// ======================
// 12. AJAX & Fetch API
// ======================
async function submitRegistration(formData) {
  try {
    const response = await fetch("https://jsonplaceholder.typicode.com/posts", {
      method: "POST",
      body: JSON.stringify(formData),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    });

    const data = await response.json();
    console.log("Registration successful:", data);
    return data;
  } catch (error) {
    console.error("Registration failed:", error);
    throw error;
  }
}

// ======================
// 13. Debugging and Testing
// ======================
function testRegistration() {
  console.group("Testing Registration");
  console.log("Initial events:", events);

  const initialSeats = events[0].seats;
  console.log(`Initial seats for event 1: ${initialSeats}`);

  registerForEventById(1);
  console.log(
    `After registration - seats: ${events[0].seats} (should be ${
      initialSeats - 1
    })`
  );

  console.assert(
    events[0].seats === initialSeats - 1,
    "Seat count not decremented correctly"
  );
  console.groupEnd();
}
document.addEventListener("DOMContentLoaded", function () {
  displayValidEvents();
  fetchEvents();
  updateSeatDisplay();

  // Add seat display to HTML if it doesn't exist
  if (!document.getElementById("seatDisplay")) {
    const seatDisplay = document.createElement("div");
    seatDisplay.id = "seatDisplay";
    document.querySelector("main").prepend(seatDisplay);
    updateSeatDisplay();
  }
});