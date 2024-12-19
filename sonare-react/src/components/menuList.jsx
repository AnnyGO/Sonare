import { Menu } from 'antd';
import {BookOutlined, HomeOutlined, TeamOutlined, UserOutlined } from '@ant-design/icons'

const MenuList = ({darkTheme}) => {
    return ( 
        <Menu theme={darkTheme ? 'dark' : 'light'} mode="inline" className='menu-bar'>
            <Menu.Item key="home" icon={<HomeOutlined />}>
                Home
            </Menu.Item>

            <Menu.SubMenu key="subtasks" icon={<BookOutlined />} title="Cursos">
                <Menu.Item key="task-1" className='item'>Todos os Cursos</Menu.Item>
                <Menu.Item key="task-2" className='item'>Exibir relatório</Menu.Item>
            </Menu.SubMenu>

            <Menu.Item key="turmas" icon={<TeamOutlined />}>
                Turmas
            </Menu.Item>

            <Menu.Item key="alunos" icon={<UserOutlined />}>
                Alunos
            </Menu.Item>

        </Menu>
    )
}

export default MenuList