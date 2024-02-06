#FROM node:18.12.0 as build
#WORKDIR /app
#COPY package*.json ./
#RUN npm install
#RUN npm install -g @angular/cli
### Replace <specific_version> with the actual version number, e.g., 13.0.0
##RUN npm install && npm install -g @angular/cli@16.0.0
#COPY . .
#RUN ng build --configuration=production
#FROM nginx:latest
#COPY --from=build app/dist/aftas-angular /usr/share/nginx/html
#EXPOSE 80


FROM node:18.12.0 as build
WORKDIR /app
COPY package*.json ./
# Increase npm's network timeout
RUN npm config set fetch-retry-maxtimeout 300000 # sets timeout to 5 minutes
# Try npm install twice to overcome transient network issues
RUN npm install || npm install
# Now install Angular CLI
RUN npm install -g @angular/cli@16.0.0
COPY . .
RUN ng build --configuration=production

FROM nginx:latest
COPY --from=build app/dist/aftas /usr/share/nginx/html
EXPOSE 80




## Stage 1: Build the Angular application
#FROM node:18.12.0 as build
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy package.json and package-lock.json (if available) to the working directory
#COPY package*.json /app/
#
## Install dependencies
#RUN npm install
#
## Copy the rest of your app's source code
#COPY . /app
#
## Build your app
#RUN npm run build -- --output-path=dist
#
## Stage 2: Serve the app with a Node.js server
#FROM node:18.12.0
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the built Angular app
#COPY --from=build /app/dist /app
#
## Install a simple HTTP server to serve static files
#RUN npm install -g http-server
#
## Expose port 4200
#EXPOSE 4200
#
## Start the server
#CMD ["http-server", "/app", "-p", "4200"]
