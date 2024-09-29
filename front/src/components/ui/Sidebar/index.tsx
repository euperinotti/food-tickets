"use client";

import { useState } from "react";
import {
  LuChevronLeft,
  LuChevronRight,
  LuGithub,
  LuLayoutDashboard,
  LuTicket,
  LuUser,
} from "react-icons/lu";
import { SidebarItem } from "./Item";

const Sidebar = () => {
  const [isOpen, setIsOpen] = useState(true);

  return (
    <aside
      className={`h-screen transition-all duration-300 bg-gray-50 dark:bg-gray-800 ${
        isOpen ? "w-64" : "w-20"
      } border-r border-gray-200`}
    >
      <div className="h-full flex flex-col justify-between">
        <div className="px-3 py-4 overflow-y-auto">
          <a href="/" className="flex items-center p-4 mb-5">
            <img
              src="/logo.svg"
              className={`w-full transition-all duration-300 ${
                !isOpen && "opacity-0"
              }`}
              alt="Flowbite Logo"
            />
          </a>

          <ul className="space-y-2">
            <SidebarItem
              icon={LuLayoutDashboard}
              label="Dashboard"
              href="/"
              isOpen={isOpen}
            />
            <SidebarItem
              icon={LuUser}
              label="Funcionários"
              href="/funcionarios"
              isOpen={isOpen}
            />
            <SidebarItem
              icon={LuTicket}
              label="Tickets"
              href="/tickets"
              isOpen={isOpen}
            />
          </ul>
        </div>

        <div className="px-3 py-4 flex flex-col">
          <ul className="space-y-2">
            <SidebarItem
              icon={LuGithub}
              label="Github"
              href="https://github.com/seu-repositorio"
              isOpen={isOpen}
            />
          </ul>

          <button
            onClick={() => setIsOpen(!isOpen)}
            className="flex items-center justify-center w-full p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700"
          >
            {isOpen ? (
              <LuChevronLeft className="text-2xl" />
            ) : (
              <LuChevronRight className="text-2xl" />
            )}
          </button>
        </div>
      </div>
    </aside>
  );
};

export default Sidebar;
