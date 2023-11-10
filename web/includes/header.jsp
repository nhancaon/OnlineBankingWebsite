<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="./styles/main.css" />
        <script src="./styles/tailwindcss.js"></script>
        <link rel="icon" href="./assets/logo.png" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" />
        <link
            href="https://fonts.googleapis.com/css2?family=Merriweather+Sans:wght@400;500;600;700&family=Ubuntu:wght@400;500;700&display=swap"
            rel="stylesheet"
            />
        <script>
            tailwind.config = {
                theme: {
                    extend: {
                        colors: {
                            primary: "#9333ea",
                            backdrop: "#f0f1f1",
                            blueSky: "#2a6ebe",
                            greenBtn: "#038883",
                            blur: "rgba(0, 0, 0, 0.5)",
                        },
                        keyframes: {
                            "bounce-custom": {
                                "0%, 100%": {
                                    transform: "translateY(-2%)",
                                },
                                "50%": {
                                    transform: "translateY(0)",
                                },
                            },
                        },
                        animation: {
                            "bounce-custom": "bounce-custom 3s infinite",
                        },
                    },
                },
            };
        </script>
        <title>SmartBanking</title>
    </head>
    <body>

        <div id="header" class="shadow-xl shadow-gray-900 bg-[#fff]">
            <div class="grid grid-cols-3 justify-center mx-56 py-4">
                <div class="w">
                    <form>
                        <div class="relative">
                            <div
                                class="absolute inset-y-0 left-0 flex items-center  pointer-events-none"
                            >
                                <img src="./assets/search.svg" class="w-5 h-5" />
                            </div>
                            <input
                                type="search"
                                id="default-search"
                                class="block w-full p-4 pl-8 text-sm text-gray-90 outline-none border-gray-200"
                                placeholder="Find in SmartBanking"
                                required
                                />
                        </div>
                    </form>
                </div>
                <div class="flex items-center justify-center">
                    <img src="./assets/logo.png" class="w-18 h-12" />
                </div>
                <div class="flex items-center justify-end">
                    <div class="grid mr-2">
                        <div class="text-sm">Good morning!</div>
                        <div class="font-bold text-lg">Chau Gia Dat</div>
                    </div>
                    <div>
                        <div class="bg-black rounded-full w-10 h-10"></div>
                    </div>
                </div>
            </div>

        </div>