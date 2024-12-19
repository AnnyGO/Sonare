import { useState } from 'react';
import { Layout, theme } from 'antd';
import Logo from './components/logo';
import MenuList from './components/menuList';
import ToggleThemeButton from './components/ToggleThemeButton';
import { Outlet } from 'react-router-dom';

const { Header, Sider } = Layout;

function App() {
  const [darkTheme, setDarkTheme] = useState(true)

  const {
    token: { colorBgContainer },
  } = theme.useToken();

  const toggleTheme = () => {
    setDarkTheme(!darkTheme)
  }

  return (
    <>
      <Layout>
        <Sider theme={darkTheme ? 'dark' : 'light'} className='sidebar'>
          <Logo />
          <MenuList darkTheme={darkTheme} />
          <ToggleThemeButton darkTheme={darkTheme} toggleTheme={toggleTheme} />
        </Sider>
        <Layout>
          <Header style={{ padding: 0, background: colorBgContainer }}>
          </Header>
          <Outlet />
        </Layout>
      </Layout>
    </>
  )
}

export default App
