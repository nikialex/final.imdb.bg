import axios from 'axios';
import { CAR_SERVICE } from './config';




export const api = {
  createCar: async function(accessToken,
    name,
    year,
    genre,
    picture_path,
    trailer_url,
    actors) {
    return axios.post(CAR_SERVICE,  {
      accessToken,
      name,
      year,
      genre,
      picture_path,
      trailer_url,
      actors
      }, {
      headers: {
        'Authorization': 'Bearer ' + accessToken,
        'Content-Type': 'application/json'
      }, 
    }   
    );
  },
};
