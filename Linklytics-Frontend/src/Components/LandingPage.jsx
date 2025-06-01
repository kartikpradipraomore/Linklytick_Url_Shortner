import React from "react";
import Card from "./Card";
import { motion } from "framer-motion";
import { useNavigate } from "react-router-dom";
import { useStoreContext } from "./ContextApi";


const LandingPage = () => {
  let desc =
    "Generate short, memorable links with ease using Linklytics’s intuitive interface. Share URLs effortlessly across platforms. Optimize your sharing strategy with Linklytics. Track clicks and manage your links seamlessly to enhance your online presence. Generate short, memorable links with ease using Linklytics’s intuitive interface. Share URLs effortlessly across platforms.";

  const navigate = useNavigate();
  const { token } = useStoreContext();
  console.log("TOKEN FROM LANDING PAGE: " + token);
  

    const dashBoardNavigateHandler = () => {};

  return (
    <div className="min-h-[calc(100vh-64px)] lg:px-14 sm:px-8 px-4">
      <div className="flex flex-row lg:py-5 pt-16 lg:gap-10 gap-8 justify-between items-center">
        <div className="flex-1">
          <motion.h1
            initial={{ opacity: 0, y: -80 }}
            whileInView={{
              opacity: 1,
              y: 0,
            }}
            viewport={{ once: true }}
            transition={{ duration: 0.9 }}
            className=" w-full font-bold font-roboto text-pink-800 md:text-5xl text-3xl mg:leading[55px] sm:leading-[45px] leading-10 lg:w-full md:w-[60%]"
          >
            Linklytics Make Url Shortning Easy For Sharing and Analysing.
          </motion.h1>

          <motion.p
            initial={{ opacity: 0, y: -80 }}
            whileInView={{
              opacity: 1,
              y: 0,
            }}
            viewport={{ once: true }}
            transition={{ duration: 0.8 }}
            className="mt-5 w-full text-slate-700 text-sm my-0"
          >
            Linklytics streamlines the process of URL shortening, making sharing
            links effortless and efficient. With its user-friendly interface,
            Linklytics allows you to generate concise, easy-to-share URLs in
            seconds. Simplify your sharing experience with Linklytics today.
          </motion.p>

          <div className="mt-5 w-full flex justify-start gap-3">
            <motion.button
              initial={{ opacity: 0, x: -80 }}
              whileInView={{
                opacity: 1,
                x: 0,
              }}
              viewport={{ once: true }}
              transition={{ duration: 0.8 }}
              onClick={dashBoardNavigateHandler}
              className="w-40 text-white rounded-md py-2 bg-gradient-to-r from-pink-500 via-red-500 to-yellow-500"
            >
              Create Link
            </motion.button>
            <motion.button
              initial={{ opacity: 0, x: -80 }}
              whileInView={{
                opacity: 1,
                x: 0,
              }}
              viewport={{ once: true }}
              transition={{ duration: 0.8 }}
              onClick={dashBoardNavigateHandler}
              className="border-btnColor border w-40 text-btnColor rounded-md py-2"
            >
              Manage ShortLink
            </motion.button>
          </div>
        </div>

        <motion.div
          initial={{ opacity: 0, x: 80 }}
          whileInView={{
            opacity: 1,
            x: 0,
          }}
          viewport={{ once: true }}
          transition={{ duration: 0.8 }}
          className="flex-1 flex justify-center w-full"
        >
          <img
            className="sm:[480px] w-[600px] object-center rounded-l-3xl"
            src="/images/img2.png"
            alt=""
          />
        </motion.div>
      </div>

      <div className="sm:pt-12 pt-7">
        <motion.p
          initial={{ opacity: 0, y: -80 }}
          whileInView={{
            opacity: 1,
            y: 0,
          }}
          viewport={{ once: true }}
          transition={{ duration: 0.8 }}
          className="text-slate-800 font-roboto font-bold lg:w-[60%]  md:w-[70%] sm:w-[80%] mx-auto text-3xl text-center"
        >
          Trusted by individuals and teams at the world best companies.
        </motion.p>

        <div className="pt-4 pb-7 grid lg:gap-7 gap-4 xl:grid-cols-4  lg:grid-cols-3 sm:grid-cols-2 grid-cols-1 mt-4">
          <Card
            title="Simple URL Shortening"
            desc="Experience the ease of creating short, memorable URLs in just a few clicks. Our intuitive interface and quick setup process ensure you can start shortening URLs without any hassle."
          />
          <Card
            title="Powerful Analytics"
            desc="Gain insights into your link performance with our comprehensive analytics dashboard. Track clicks, geographical data, and referral sources to optimize your marketing strategies."
          />
          <Card
            title="Enhanced Security"
            desc="Rest assured with our robust security measures. All shortened URLs are protected with advanced encryption, ensuring your data remains safe and secure."
          />
          <Card
            title="Fast and Reliable"
            desc="Enjoy lightning-fast redirects and high uptime with our reliable infrastructure. Your shortened URLs will always be available and responsive, ensuring a seamless experience for your users."
          />
        </div>
      </div>
    </div>
  );
};

export default LandingPage;
