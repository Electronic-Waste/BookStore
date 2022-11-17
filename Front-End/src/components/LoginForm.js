import React from "react";
import "../css/login.css"
import {login, register} from "../services/userService";
import {Link} from "react-router-dom";
import {useNavigate} from "react-router";
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { Button, Checkbox, Form, Input } from 'antd';


export function LoginForm ()
{
    const navigate = useNavigate();

    const handleLogin = userInfo => {
        // console.log(userInfo);
        login(userInfo, navigate);
    }

    // return (
    //     <form name="Login" method="get" className="loginForm">
    //         <input type="text" id="account" placeholder="Account"/>
    //         <br/>
    //         <input type="password" id="password" placeholder="Password"/>
    //         <br/><br/>
    //         <div style={{textAlign: "center"}}>
    //             <input type="button" value="Login" onClick={handleLogin}/>
    //         </div>
    //     </form>
    //     );

    return (
        <Form
            name="normal_login"
            className="login-form"
            initialValues={{
                remember: true,
            }}
            onFinish={handleLogin}
        >
            <Form.Item
                name="userId"
                rules={[
                    {
                        required: true,
                        message: 'Please input your Account!',
                    },
                ]}
            >
                <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Account" />
            </Form.Item>
            <Form.Item
                name="password"
                rules={[
                    {
                        required: true,
                        message: 'Please input your Password!',
                    },
                ]}
            >
                <Input
                    prefix={<LockOutlined className="site-form-item-icon" />}
                    type="password"
                    placeholder="Password"
                />
            </Form.Item>
            <Form.Item>
                <Form.Item name="remember" valuePropName="checked" noStyle>
                    <Checkbox>Remember me</Checkbox>
                </Form.Item>

                <a className="login-form-forgot" href="">
                    Forgot password
                </a>
            </Form.Item>

            <Form.Item>
                <Button type="primary" htmlType="submit" className="login-form-button">
                    Log in
                </Button>
                Or <a href="/register">register now!</a>
            </Form.Item>
        </Form>
    );

}

export function RegisterForm()
{
    const navigate = useNavigate();

    const handleRegister = values => {
        console.log("Form data: ", values);
        register(values);
    }

    // return (
    //     <form name="Login" method="get" className="loginForm">
    //         <input type="text" id="username" placeholder="UserName"/><br/>
    //         <input type="text" id="email" placeholder="Email"/><br/>
    //         <input type="password" id="password1" placeholder="Password"/><br/>
    //         <input type="password" id="password2" placeholder="Confirm Password"/><br/>
    //         <br/><br/>
    //         <div style={{textAlign: "center"}}>
    //             <input type="button" value="Register Now!" onClick={handleRegister}/>
    //         </div>
    //     </form>
    // )

    return (
        <Form
            name="register"
            onFinish={handleRegister}
            scrollToFirstError
        >
            <Form.Item
                name="email"
                label="E-mail"
                rules={[
                    {
                        type: 'email',
                        message: 'The input is not valid E-mail!',
                    },
                    {
                        required: true,
                        message: 'Please input your E-mail!',
                    },
                ]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                name="password"
                label="Password"
                rules={[
                    {
                        required: true,
                        message: 'Please input your password!',
                    },
                ]}
                hasFeedback
            >
                <Input.Password />
            </Form.Item>

            <Form.Item
                name="confirm"
                label="Confirm Password"
                dependencies={['password']}
                hasFeedback
                rules={[
                    {
                        required: true,
                        message: 'Please confirm your password!',
                    },
                    ({ getFieldValue }) => ({
                        validator(_, value) {
                            if (!value || getFieldValue('password') === value) {
                                return Promise.resolve();
                            }
                            return Promise.reject(new Error('The two passwords that you entered do not match!'));
                        },
                    }),
                ]}
            >
                <Input.Password />
            </Form.Item>

            <Form.Item
                name="username"
                label="Username"
                tooltip="What do you want others to call you?"
                rules={[{ required: true, message: 'Please input your username!', whitespace: true }]}
            >
                <Input />
            </Form.Item>
            <Form.Item
                name="agreement"
                valuePropName="checked"
                rules={[
                    {
                        validator: (_, value) =>
                            value ? Promise.resolve() : Promise.reject(new Error('Should accept agreement')),
                    },
                ]}
                style={{textAlign:"center"}}
            >
                <Checkbox>
                    I have read the <a href="">agreement</a>
                </Checkbox>
            </Form.Item>
            <Form.Item>
                <Button type="primary" htmlType="submit" className="login-form-button">
                    Register
                </Button>
            </Form.Item>
        </Form>
    );

}
