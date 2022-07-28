import React, { memo } from 'react';
import Table from '../components/Table/table';

const Income = memo((props) => {
    return(
        <>
        {/* <LineChart category='income' /> */}
        <Table data={props.data}/>
        </>
    );
})

export default Income;