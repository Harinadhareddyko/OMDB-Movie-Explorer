#  OMDB Movie Explorer  

A full-stack movie search application built with **Java Servlets + HTML/CSS/JavaScript**, connected to the **OMDB API**.  
It allows users to search movies, view details, explore trending picks, and manage favorites — with a fast in-memory caching layer.

---

##  Features

###  **Frontend**
- Royal-purple cinematic UI  
- 3×2 full-screen movie grid (6 cards per page)  
- Landscape movie cards with 3D hover effects  
- Responsive layout for all screen sizes  
- Search bar with pagination  
- Filters (Year, Type, Sort)  
- Trending movies on home  
- Movie details modal (rating, plot, cast, etc.)  
- Favorites stored in LocalStorage  
- Smooth loader animation  
- Clean animated UI components  

###  **Backend (Java)**
- Java Servlets  
- In-memory LRU cache  
- Cache expiry + max size  
- REST-style endpoints:
  - `/api/search?query=&page=`
  - `/api/movie?id=`
- API key secured through servlet config  
- Follows clean MVC structure  
- Runs on Apache Tomcat  

---

## 📌 Tech Stack

### **Frontend**
- HTML5  
- CSS3  
- Vanilla JavaScript  

### **Backend**
- Java 8+  
- Servlets  
- Tomcat Server  

### **External API**
- OMDB API — https://www.omdbapi.com/

---

