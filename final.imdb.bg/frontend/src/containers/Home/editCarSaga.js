import { EDIT_CAR_REQUEST } from './types.js';
import { editCarSuccess, editCarError } from './actions.js';
import { put, take, call } from 'redux-saga/effects';

/** Call editCar  */
export default function* getCarsSaga(api) {
  while (true) {
    const editCarRequest = yield take(EDIT_CAR_REQUEST);
    if (editCarRequest.payload) {
      const { 
        accessToken,
        id,
        name,
      year,
      genre,
      picture_path,
      image,
      trailer_url,
      actors,
    
      } = editCarRequest.payload;

    
      yield call(
        editCar,
        api,
        accessToken,
        id,
        name,
        year,
        genre,
        picture_path,
        image,
        trailer_url,
        actors,
       
      );
    }
  }
}

/** Create editCar request
 * @return {any} Object with updated car 
 */
function* editCar(api, 
  accessToken,
  id,
  name,
  year,
  genre,
  picture_path,
  image,
  trailer_url,
  actors,
  ) {
  var response;

  if (typeof(image)=='undefined'){
    image="no image";
  }

   console.log("accessToken",accessToken)
   console.log("id",id)
   console.log("name",name)
   console.log("year",year)
   console.log("genre",genre)
   console.log("picture_path",picture_path)
   console.log("image",image)
   console.log("trailer_url",trailer_url)
   console.log("actors",actors)
  try {
    response = yield call(
      api.editCar,
      accessToken,
      id,
      name,
      year,
      genre,
      picture_path,
      trailer_url,
      actors,
      );
    
    yield put(editCarSuccess(response.data));
  } catch (error) {
    yield put(editCarError(error));
  }
}
