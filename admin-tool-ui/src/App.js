// in src/App.js
import * as React from "react";
import { Admin, Resource, ListGuesser } from 'react-admin';
import {DoctorList}  from './doctors';

// import jsonServerProvider from 'ra-data-json-server';

import dataProvider from './dataProvider';

// const dataProvider = jsonServerProvider('https://jsonplaceholder.typicode.com');
const App = () => (
    <Admin dataProvider={dataProvider}>
        <Resource name="doctors" list={DoctorList} />
    </Admin>
);
export default App;