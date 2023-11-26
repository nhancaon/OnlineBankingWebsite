<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String root = request.getContextPath();%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="../styles/main.css" />
    <script src="../styles/tailwindcss.js"></script>
    <link rel="icon" href="../assets/logo.png" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    />
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
            animation: {
              bounceOne: "0.5s ease-in-out",
              appear: "appear 0.5s ease-in-out",
              appearRight: "appearRight ease-in-out .3s",
              notification:
                "notification ease-in-out .3s, fadeOut 3s .3s ease-in-out forwards",
            },
            keyframes: {
              bounceOne: {
                "0%": { transform: "translateY(0)" },
                "100%": { transform: "translateY(-20px)" },
              },
              appear: {
                "0%": { opacity: "0", transform: "translateY(20%)" },
                "100%": { opacity: "1", transform: "translateY(0)" },
              },
              appearRight: {
                "0%": { opacity: "0", transform: "translateX(20%)" },
                "100%": { opacity: "1", transform: "translateX(0)" },
              },
              notification: {
                "0%": { opacity: "0", transform: "translateX(20%)" },
                "100%": { opacity: "1", transform: "translateX(0)" },
              },
              fadeOut: { "0%": { opacity: "1" }, "100%": { opacity: "0" } },
            },
          },
        },
      };
    </script>
    <title>Banking</title>
  </head>
  <body>
    <nav
      class="fixed top-0 z-50 w-full bg-white border-b border-gray-200 dark:bg-gray-800 dark:border-gray-700"
    >
      <div class="px-3 py-3 lg:px-5 lg:pl-3">
        <div class="flex items-center justify-between">
          <div class="flex items-center justify-start rtl:justify-end">
            <button
              data-drawer-target="logo-sidebar"
              data-drawer-toggle="logo-sidebar"
              aria-controls="logo-sidebar"
              type="button"
              class="inline-flex items-center p-2 text-sm text-gray-500 rounded-lg sm:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
            >
              <span class="sr-only">Open sidebar</span>
              <svg
                class="w-6 h-6"
                aria-hidden="true"
                fill="currentColor"
                viewBox="0 0 20 20"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  clip-rule="evenodd"
                  fill-rule="evenodd"
                  d="M2 4.75A.75.75 0 012.75 4h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 4.75zm0 10.5a.75.75 0 01.75-.75h7.5a.75.75 0 010 1.5h-7.5a.75.75 0 01-.75-.75zM2 10a.75.75 0 01.75-.75h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 10z"
                ></path>
              </svg>
            </button>
            <a href="./" class="flex ms-2 md:me-24">
              <img
                src="../assets/logo.png"
                class="h-12 me-3"
                alt="FlowBite Logo"
              />
            </a>
          </div>
        </div>
      </div>
    </nav>

    <aside
      id="logo-sidebar"
      class="fixed top-0 left-0 z-40 w-64 h-screen pt-20 transition-transform -translate-x-full bg-white border-r border-gray-200 sm:translate-x-0 dark:bg-gray-800 dark:border-gray-700"
      aria-label="Sidebar"
    >
      <div class="h-full px-3 pb-4 overflow-y-auto bg-gray-800">
        <ul class="space-y-2 font-medium">
          <li>
            <form action="customer" method="GET">
              <input type="hidden" name="action" value="show-customer">
              <button
                type="submit"
                class="flex items-center p-2 rounded-lg text-white hover:bg-gray-700 group"
              >
                <svg
                  class="flex-shrink-0 w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
                  aria-hidden="true"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="currentColor"
                  viewBox="0 0 20 18"
                >
                  <path
                    d="M14 2a3.963 3.963 0 0 0-1.4.267 6.439 6.439 0 0 1-1.331 6.638A4 4 0 1 0 14 2Zm1 9h-1.264A6.957 6.957 0 0 1 15 15v2a2.97 2.97 0 0 1-.184 1H19a1 1 0 0 0 1-1v-1a5.006 5.006 0 0 0-5-5ZM6.5 9a4.5 4.5 0 1 0 0-9 4.5 4.5 0 0 0 0 9ZM8 10H5a5.006 5.006 0 0 0-5 5v2a1 1 0 0 0 1 1h11a1 1 0 0 0 1-1v-2a5.006 5.006 0 0 0-5-5Z"
                  />
                </svg>
                <span class="ms-3">Customer</span>
              </button>
            </form>
          </li>
          <li>
            <form action="paymentAccount" method="GET">
              <input type="hidden" name="action" value="show-paymentAccount">
              <button
                type="submit"
                class="flex items-center p-2 rounded-lg text-white hover:bg-gray-700 group"
              >
                <svg
                  class="flex-shrink-0 w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
                  aria-hidden="true"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="currentColor"
                  viewBox="0 0 20 18"
                >
                  <path
                    d="M14 2a3.963 3.963 0 0 0-1.4.267 6.439 6.439 0 0 1-1.331 6.638A4 4 0 1 0 14 2Zm1 9h-1.264A6.957 6.957 0 0 1 15 15v2a2.97 2.97 0 0 1-.184 1H19a1 1 0 0 0 1-1v-1a5.006 5.006 0 0 0-5-5ZM6.5 9a4.5 4.5 0 1 0 0-9 4.5 4.5 0 0 0 0 9ZM8 10H5a5.006 5.006 0 0 0-5 5v2a1 1 0 0 0 1 1h11a1 1 0 0 0 1-1v-2a5.006 5.006 0 0 0-5-5Z"
                  />
                </svg>
                <span class="ms-3">Payment Account</span>
              </button>
            </form>
          </li>
          <li>
            <form action="savingAccount" method="GET">
              <input type="hidden" name="action" value="show-savingAccount">
              <button
                type="submit"
                class="flex items-center p-2 rounded-lg text-white hover:bg-gray-700 group"
              >
                <svg
                  class="flex-shrink-0 w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
                  aria-hidden="true"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="currentColor"
                  viewBox="0 0 20 18"
                >
                  <path
                    d="M14 2a3.963 3.963 0 0 0-1.4.267 6.439 6.439 0 0 1-1.331 6.638A4 4 0 1 0 14 2Zm1 9h-1.264A6.957 6.957 0 0 1 15 15v2a2.97 2.97 0 0 1-.184 1H19a1 1 0 0 0 1-1v-1a5.006 5.006 0 0 0-5-5ZM6.5 9a4.5 4.5 0 1 0 0-9 4.5 4.5 0 0 0 0 9ZM8 10H5a5.006 5.006 0 0 0-5 5v2a1 1 0 0 0 1 1h11a1 1 0 0 0 1-1v-2a5.006 5.006 0 0 0-5-5Z"
                  />
                </svg>
                <span class="ms-3">Saving Account</span>
              </button>
            </form>
          </li>
        </ul>
      </div>
    </aside>
  </body>
</html>
