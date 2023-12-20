# Aftas Project
## Overview
### Prerequisites
- Java JDK 11 or higher
- Maven
- Node.js
- Angular CLI
### Setting Up the Project
**Spring Boot Backend**
1. Clone the repository:
```
git clone https://github.com/md-naciri/aftas.git
```
2. Navigate to the backend directory:
```
cd aftas-backend
```
3. Build the project using Maven:
```
mvn clean install
```
4. Run the Spring Boot application:
```
mvn spring-boot:run
```
**Angular Frontend**
1. Navigate to the frontend directory:
```
cd aftas-frontend
```
2. Install dependencies:
```
npm install
```
3. Start the Angular server:
```
ng serve
```
Access the application at `http://localhost:4200`
## API Endpoints
### Member Endpoints
- Get Member: `GET /api/v1/member/{id}`
- Create Member: `POST /api/v1/member`
- Search for Member: `GET /api/v1/member/search/{keyword}`
- Get All Members: `GET /api/v1/member`
### Competition Endpoints
- Create Competition: `POST /api/v1/competition`
- Get Competition: `GET /api/v1/competition/{code}`
- Get All Competitions: `GET /api/v1/competition`
- Get Competitions with Pagination: `GET /api/v1/competition/pagination?page={page}&size={size}`
- Filter Competitions: `GET /api/v1/competition/filter/{status}`
### Ranking Endpoints
- Get Ranking: `GET /api/v1/ranking/member/{memberId}/competition/{competitionCode}`
- Register Member for Competition: `POST /api/v1/ranking`
- Get Competition Ranks: `GET /api/v1/ranking/competition/{competitionCode}`
### Hunting
- Hunting: `POST /api/v1/hunting`
### Additional Endpoints
- Level Management: `POST /api/v1/level`
- Fish Management: `POST /api/v1/fish`

