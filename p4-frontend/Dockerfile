FROM node:14
WORKDIR /var/lib/jenkins/workspace/test_pipeline_with_docker/p4-frontend
#COPY package*.json ./
COPY . /var/lib/jenkins/workspace/test_pipeline_with_docker
RUN pwd
RUN ls -a
RUN npm ci --only=production
#COPY . /usr/src/app
EXPOSE 80
RUN npm install -g npm@latest
RUN npm install
RUN npm run build
CMD ["npm" ,"start"]
#CMD [ "node", "server.js" ]