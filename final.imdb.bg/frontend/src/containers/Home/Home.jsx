import React, { useEffect } from "react";
import MaterialTable from "material-table";
import Header from "../../components/Header/Header.jsx";
import { isEmpty } from "lodash";
import {
  getCars,
  editCar,
  removeCar,
  createCar
} from "../../containers/Home/actions";
import { useSelector, useDispatch } from "react-redux";
import "./Home.scss";

export default function MaterialTableDemo() {
  const dispatch = useDispatch();

  const { cars } = useSelector(state => state.getCars);
  const { currentUser, userId, accessToken, firstName, lastName } = useSelector(
    state => state.userSession
  );

  const [allCars, setCars] = React.useState(cars);
  const [selsectedRow, setSelectedRow] = React.useState([]);

  // const { trailer, setTrailer } = React.useState();

  /** Create request to get all Cars */
  useEffect(() => {
    dispatch(getCars());
    setCars(cars);
  }, [!isEmpty(cars)]);

  /** Update car request */
  function onEdit(newData, oldData) {
    const {
      id,
      name,
      year,
      genre,
      picture_path,
      trailer_url,
      actors
    } = newData;

    setCars(prevState => {
      const data = [...prevState];

      data[data.indexOf(oldData)] = newData;
      dispatch(
        editCar(
          accessToken,
          id,
          name,
          year,
          genre,
          picture_path,
          trailer_url,
          actors
        )
      );

      return data;
    });
  }

  /** Remove car request */
  function deleteCar(oldData) {
    dispatch(removeCar(oldData.id, accessToken));
  }

  /** get Image  */
  function getImage(Data) {
    let base64Image = "data:image/png;base64," + Data.image;
    return base64Image;
  }

  /** Create new car request */
  function createNewCar(newData) {
    const { name, year, genre, picture_path, trailer_url, actors } = newData;

    dispatch(
      createCar(
        accessToken,
        name,
        year,
        genre,
        picture_path,
        trailer_url,
        actors
      )
    );
  }

  return (
    <div>
      <Header />
      <MaterialTable
        title="Movies"
        data={allCars}
        columns={[
          {
            field: "url",
            title: "",

            render: rowData => (
              <img src={getImage(rowData)} style={{ width: 50 }} />
            )
          },

          { title: "Name", field: "name" },

          {
            title: "Actors", field: "actors"
          },
          { title: "Year", field: "year", type: "numeric" },
          {
            title: "Genre",
            field: "genre",
            lookup: { Comedy: "Comedy", Action: "Action" }
          },
          {
            title: "Picture path",
            field: "picture_path"
          },
          {
            title: "Trailer Url",
            field: "trailer_url"
          }
        ]}
        options={{
          selection: true
        }}
        onSelectionChange={rows => setSelectedRow(rows)}
        editable={{
          onRowAdd: currentUser
            ? newData =>
                new Promise(resolve => {
                  setTimeout(() => {
                    resolve();
                    setCars(prevState => {
                      const data = [...prevState];
                      //  data.push(newData);
                      createNewCar(newData);
                      data.push(newData);
                      return data;
                    });
                  }, 600);
                })
            : null,
          onRowUpdate: currentUser
            ? (newData, oldData) =>
                new Promise(resolve => {
                  setTimeout(() => {
                    resolve();
                    if (oldData) {
                      onEdit(newData, oldData);
                    }
                  }, 600);
                })
            : null,
          onRowDelete: currentUser
            ? oldData =>
                new Promise(resolve => {
                  setTimeout(() => {
                    resolve();
                    setCars(prevState => {
                      const data = [...prevState];
                      data.splice(data.indexOf(oldData), 1);
                      deleteCar(oldData);
                      return data;
                    });
                  }, 600);
                })
            : null
        }}
        detailPanel={rowData => {
          return (
            <div
              style={{
                textAlign: "center"
              }}
            >
              <iframe
                width="640"
                height="390"
                src={rowData.trailer_url}
                frameBorder="0"
                allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                allowFullScreen
              />
            </div>
          );
        }}
        onRowClick={(event, rowData, togglePanel) => togglePanel()}
      />
    </div>
  );
}
