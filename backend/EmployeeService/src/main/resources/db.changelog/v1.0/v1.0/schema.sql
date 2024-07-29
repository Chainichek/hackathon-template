create table public.employees (
    employee_id UUID primary key,
    login text not null,
    password text not null,
    name text not null,
    lastname text not null,
    email text not null,
    role text not null,
    is_removed boolean default false
);
GO

create table public.groups (
    group_id UUID primary key,
    title text not null,
    description text,
    is_removed boolean not null default false
);
GO

create table public.groups_employees (
    id UUID primary key,
    group_id UUID,
    employee_id UUID
);
GO

alter table public.groups_employees
add foreign key(group_id) references public.groups(group_id);
GO

alter table public.groups_employees
add foreign key(employee_id) references public.employees(employee_id);