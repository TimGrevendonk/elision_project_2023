FROM node:14
WORKDIR /var/lib/jenkins/workspace/test_pipeline_with_docker/p4-frontend
COPY package*.json ./
RUN npm ci --only=production
COPY . /usr/src/app
EXPOSE 80
COPY cert.pem /etc/ssl/certs/
COPY chain.pem /etc/ssl/certs/
COPY fullchain.pem /etc/ssl/certs/
COPY privkey.pem /etc/ssl/certs/
RUN ls /etc/ssl/certs | grep .pem
RUN update-ca-certificates
RUN npm install -g npm@latest
RUN npm install
RUN npm run build
CMD ["npm" ,"start"]
#CMD [ "node", "server.js" ]