import React, { memo } from 'react';
import LineChart from '../components/Charts/LineChart';
import Table from '../components/Table/table';

const Spending = memo((props) => {
    return(
        <>
        {/* <LineChart category='spending'/> */}
        <Table data={props.data}/>
        </>
    );
});

export default Spending;