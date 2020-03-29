import { CREATE_CAR_REQUEST } from './types.js';
import { createCarSuccess, createCarError } from './actions.js';
import { put, take, call } from 'redux-saga/effects';

/** Call createCar  */
export default function* createCarSaga(api) {
  while (true) {
    const createCarRequest = yield take(CREATE_CAR_REQUEST);
    if (createCarRequest.payload) {
      const { 
        accessToken,
    name,
    year,
    genre,
    picture_path,
    trailer_url,
    actors
      } = createCarRequest.payload;

     
      yield call(
        createCar,
        api,
        accessToken,
        name,
        year,
        genre,
        picture_path,
        trailer_url,
        actors
      );
    }
  }
}

/** Create createCar request
 * @return {any} Object with updated car 
 */
function* createCar(api,accessToken,
  name,
  year,
  genre,
  picture_path,
  trailer_url,
  actors) {
  var response;
  try {
    response = yield call(
      api.createCar,
      accessToken,
      name,
      year,
      genre,
      picture_path,
      trailer_url,
      actors);
      console.log('CREATE CAR SAGA', response)
    yield put(createCarSuccess(response.data));
  } catch (error) {
    console.log('ERROR CREATE CAR SAGA', error)
    yield put(createCarError(error));
  }
}
