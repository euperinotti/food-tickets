
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
    title: 'Produtos',
    label: '',
    href: '/products',
    icon: <Ticket size={18} />,
  },
  {
    title: 'Tasks',
    label: '3',
    href: '/tasks',
    icon: <Users size={18} />,
  },
  {
    title: 'Requests',
    label: '10',
    href: '/requests',
    icon: <Users size={18} />,
    sub: [
      {
        title: 'Trucks',
        label: '9',
        href: '/trucks',
        icon: <Users size={18} />,
      },
      {
        title: 'Cargos',
        label: '',
        href: '/cargos',
        icon: <Users size={18} />,
      },
    ],
  },
  {
    title: 'Analysis',
    label: '',
    href: '/analysis',
    icon: <Users size={18} />,
  },
]
