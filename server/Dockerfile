FROM node:12-slim
COPY . .
RUN npm install --production
EXPOSE 80
CMD [ "npm", "start" ]