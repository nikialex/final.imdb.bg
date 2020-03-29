import axios from 'axios';
import { CAR_SERVICE } from './config';

export const api = {
  
  editCar: async function(accessToken,
    id,
    name,
    year,
    genre,
    picture_path,
    trailer_url,
    actors,
    ) {
    return axios.put(CAR_SERVICE, 
      {
    accessToken,
    id,
    name,
    year,
    genre,
    picture_path,
    trailer_url,
    actors,
   
      }, {
      headers: {
        'Authorization': 'Bearer ' + accessToken,
        'Content-Type': 'application/json'
      }, 
    }   
    );
  },
};



