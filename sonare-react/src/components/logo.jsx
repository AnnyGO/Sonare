import React from 'react'

import Logo from '../assets/logo2.png';

const logo = () => {
    return (
        <div className='logo'>
            <div className="logo-icon">
                <img src={Logo} alt="Sonare Logo" className="logo-image" />
            </div>
        </div>
    )
}

export default logo