// in src/users.js
import * as React from "react";
import { List, Datagrid, TextField, EmailField } from 'react-admin';

export const DoctorList = props => (
    <List {...props}>
        <Datagrid rowClick="edit">
            <TextField source="id" />
            <TextField source="name" />
        </Datagrid>
    </List>
);
