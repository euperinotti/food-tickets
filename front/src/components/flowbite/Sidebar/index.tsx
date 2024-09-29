"use client"
import { Sidebar } from "flowbite-react";
import { LuLineChart, LuTicket, LuUser } from "react-icons/lu";

export const FlowbiteSidebar = () => {
  return (
    <Sidebar aria-label="Default sidebar example" className="h-full">
      <Sidebar.Logo href="#" img="/Logo.svg" />
      <Sidebar.Items className="h-full">
        <Sidebar.ItemGroup>
          <Sidebar.Item href="#" icon={LuLineChart} active>
            Dashboard
          </Sidebar.Item>
          <Sidebar.Item
            href="#"
            icon={LuTicket}
            labelColor="dark"
          >
            Tickets
          </Sidebar.Item>
          <Sidebar.Item href="#" icon={LuUser}>
            Users
          </Sidebar.Item>
        </Sidebar.ItemGroup>
      </Sidebar.Items>
    </Sidebar>
  );
};
