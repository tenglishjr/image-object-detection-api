
# Image Object Detection API

Service that ingests user images (via image URL), analyzes them for object detection, and returns the enhanced content. This API utilizes the **Imagga image analysis API** to retrieve detected objects within images.

## Author

- [@tenglishjr](https://www.github.com/tenglishjr)

## API Reference

#### Get all images (optional: get all by objects)

```http
  GET /api/v1/images
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `?objects` | `string` | Query parameter to filter images (ex: "dog,cat,mouse")|

#### Get image

```http
  GET /api/v1/images/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of image to fetch|

#### Get image object detection & Save Image

```http
  POST /api/v1/images
```

| Property | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `imageUrl`      | `string` | **Required**. URL of the image for object detection.|
| `label`      | `string` | Optional label for image.|
| `useObjectDetection`      | `boolean` | Flag to get detected objects for image|


## Run Locally

#### Prerequisites:
- Java 17
- Docker

Clone the project

```bash
  git clone git@github.com:tenglishjr/image-object-detection-api.git
```

Go to the project directory

```bash
  cd my-project
```

Start Docker container

```bash
  docker compose up
```

Start application

```bash
  ./gradlew clean build bootRun
```


## Running Tests

To run tests, run the following command

```bash
  ./gradlew test
```

## Still to come...

- Additional Tests
- CI/CD configuration
- Image upload option _(vs by URL only)_
- `UPDATE` endpoint to get tags for images saved to DB with detection flag initially set to `false`
- `DELETE` endpoint

