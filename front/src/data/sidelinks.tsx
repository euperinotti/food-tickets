
import { LayoutDashboard } from 'lucide-react'

import { Ticket, Users } from 'lucide-react'

export interface NavLink {
  title: string
  label?: string
  href: string
  icon: JSX.Element
}

export interface SideLink extends NavLink {
  sub?: NavLink[]
}

export const sidelinks: SideLink[] = [
  {
    title: 'Dashboard',
    label: '',
    href: '/',
    icon: <LayoutDashboard size={18} />,
  },
  {
    title: 'Tickets',
    label: '',
    href: '/tickets',
    icon: <Ticket size={18} />,
  },
  {
    title: 'Funcionários',
    label: '3',
    href: '/employees',
    icon: <Users size={18} />,
  },
]
