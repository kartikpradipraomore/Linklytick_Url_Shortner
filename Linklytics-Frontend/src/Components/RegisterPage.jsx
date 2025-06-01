import React, { useState } from 'react'
import { useForm } from "react-hook-form";
import TextField from "./TextField"
import { Link, useNavigate } from 'react-router-dom';
import toast from 'react-hot-toast';
import api from '../api/api';

const RegisterPage = () => {

  const navigate = useNavigate();
  const [loader, setLoader] = useState(false);

    const {
      register,
      handleSubmit,
      reset,
      formState: { errors }, 
    } = useForm({
      defaultValues: {
        username: "",
        email: "",
        password: "",
      },
      mode: "onTouched",
    });
  
  const registerHandler = async (data) => {

    setLoader(true);
    try {
      const { data: response } = await api.post("/api/auth/register", data);
      reset();
      navigate("/login")
      toast.success("Registraction successful!")
    } catch (error) {
      console.log(error);
      toast.error("Registraction failed");
    } finally {
      setLoader(false);
    }
      
  };

  return (
    <div className="min-h-[calc(100vh-64px)] flex justify-center items-center">
      <form
        onSubmit={handleSubmit(registerHandler)}
        className="w-full max-w-sm sm:max-w-md mx-auto bg-white shadow-lg rounded-2xl p-6 sm:p-8"
      >
        <h1 className="text-center text-3xl font-semibold text-gray-800 font-serif mb-2">
          Register Here
        </h1>
        <hr className="border-gray-300 mb-6" />

        <div className="flex flex-col gap-5">
          <TextField
            label="UserName"
            required
            id="userName"
            type="text"
            message="*Username is required"
            placeholder="Type your username"
            register={register}
            errors={errors}
          />

          <TextField
            label="Email"
            required
            id="email"
            type="email"
            message="*Email is required"
            placeholder="Type your email"
            register={register}
            errors={errors}
          />

          <TextField
            label="Password"
            required
            id="password"
            type="password"
            message="*Password is required"
            placeholder="Type your password"
            register={register}
            min={6}
            errors={errors}
          />
        </div>

        <button
          disabled={loader}
          type="submit"
          className={`cursor-pointer mt-4 w-full py-2 px-4 rounded-md text-white hover:text-black font-medium  bg-gradient-to-r from-purple-600 via-pink-500 to-red-500 transition duration-200`}
        >
          {loader ? "Loading..." : "Register"}
        </button>

        <p className="text-center text-sm text-slate-700 mt-6">
          Already have an account?
          <Link
            className="font-semibold underline hover:text-black"
            to="/login"
          >
            <span className="text-btnColor"> Login</span>
          </Link>
        </p>
      </form>
    </div>
  );
}

export default RegisterPage