import React from "react";
import {HeadBar} from "../components/HeadBar";
import {SideBar} from "../components/SideBar";
import {UserExcel} from "../components/UserExcel";
import {getUsers} from "../services/userService";

export class UserManageView extends React.Component {

    render() {
        return (
            <div>
                <header>
                    <HeadBar/>
                </header>
                <aside>
                    <SideBar/>
                </aside>
                <section>
                    <UserExcel/>
                </section>
            </div>
        );
    }

}
