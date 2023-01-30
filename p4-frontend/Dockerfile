FROM node:14
WORKDIR /p4-frontend
#COPY package*.json ./
COPY /p4-frontend/package.json ./
COPY /p4-frontend/package-lock.json ./
RUN pwd
RUN ls -a
COPY /p4-frontend /p4-frontend
#COPY . /usr/src/app
EXPOSE 8080:8080
ENV PORT 8080:8080
RUN npm install -g npm@latest
RUN npm install
RUN npm ci
RUN npm run build
CMD ["npm" ,"start"]
#CMD [ "node", "server.js" ]