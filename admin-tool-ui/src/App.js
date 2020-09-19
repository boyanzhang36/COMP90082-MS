import React, { Component } from "react";
import { Admin, Resource, ListGuesser } from "react-admin";
import jsonServerProvider from "ra-data-json-server";
import doctors from "./doctors"

const dataProvider =
  jsonServerProvider("localhost:8081/api/v1/doctors");

class App extends Component {
  render() {
    return (
      <Admin dataProvider={dataProvider}>
        <Resource name="doctors" list={doctors} />
      </Admin>
    );
  }
}

export default App;