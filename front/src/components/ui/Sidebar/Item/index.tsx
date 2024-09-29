import { IconType } from "react-icons";

export interface SidebarItemProps {
  icon: IconType;
  label: string;
  href: string;
  isOpen: boolean;
}

export const SidebarItem = ({
  icon,
  label,
  href,
  isOpen,
}: SidebarItemProps) => {
  const Icon = icon({ size: isOpen ? "" : "22px", className: isOpen ? "" : "w-full" });

  return (
    <li>
      <a
        href={href}
        className={`flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 transition-all duration-300`}
      >
        {Icon}
        {isOpen && <span className="ml-3">{label}</span>}
      </a>
    </li>
  );
};
