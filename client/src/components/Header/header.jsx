import React, { memo } from 'react';
import styles from './header.module.css';
const Header = memo((props) => {
    return (
        <div className={styles.container}>
            <h1 className={styles.left}> {props.title} </h1>
        </div>
    );
});

export default Header;