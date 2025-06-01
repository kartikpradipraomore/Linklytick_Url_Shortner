import { useState } from "react";
import { useForm } from "react-hook-form";
import toast from "react-hot-toast";
import { Link, useNavigate } from "react-router-dom";
import api from "../api/api";
import TextField from "./TextField";
import { useStoreContext } from "./ContextApi";

const LoginPage = () => {
  const navigate = useNavigate();
  const [loader, setLoader] = useState(false);
  const { setToken } = useStoreContext();

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

  const loginHandler = async (data) => {
    setLoader(true);
    try {
      const { data: response } = await api.post("/api/auth/login", data);
      // Setting inside context
      setToken(response.token);

      // Store the token in local storage
      console.log(response.token);
      localStorage.setItem("JWT_TOKEN", JSON.stringify(response.token));

      toast.success("Login successful!");
      reset();
      navigate("/dashboard");
    } catch (error) {
      console.log(error);
      toast.error("Login failed");
    } finally {
      setLoader(false);
    }
  };

  return (
    <div className="min-h-[calc(100vh-64px)] flex justify-center items-center">
      <form
        onSubmit={handleSubmit(loginHandler)}
        className="w-full max-w-sm sm:max-w-md mx-auto bg-white shadow-lg rounded-2xl p-6 sm:p-8"
      >
        <h1 className="text-center text-3xl font-semibold text-gray-800 font-serif mb-2">
          Login Here
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
          {loader ? "Loading..." : "Login"}
        </button>

        <p className="text-center text-sm text-slate-700 mt-6">
          Don't have an account?
          <Link
            className="font-semibold underline hover:text-black"
            to="/register"
          >
            <span className="text-btnColor"> Register </span>
          </Link>
        </p>
      </form>
    </div>
  );
};

export default LoginPage;
