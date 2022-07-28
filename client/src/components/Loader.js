import React from 'react';
import ReactLoading from 'react-loading';
function Loader({type, message}) {
    return (
        <div class="contentWrap" style={{
        widht:"100%",
        height:"100%"}}>
            <div style={{
                position: "sticky",
                top: "40%",    
                left: "50%",  
                display: "flex",
                flexDirection: 'column',
                justifyContent: "center",
                alignItems: "center"
                }}>                          
                <h2
                style={{
                    fontSize:'1rem'
                }}>{message}</h2>
                <ReactLoading
                    type={type}
                    color={'#558CCF'}
                    height={'10%'}
                    width={'10%'} />
                </div>
            </div>
            );
        }
export default Loader;