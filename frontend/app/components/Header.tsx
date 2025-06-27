'use client';
import React, { useContext, useEffect, useRef } from 'react';
import Link from 'next/link';
import { AuthContext } from '../context/AuthContext';

const Header = () => {
  const menuRef = useRef<HTMLDivElement>(null);
  const buttonRef = useRef<HTMLButtonElement>(null);

  const { isLoggedIn, user, logout } = useContext(AuthContext);

  const [menuOpen, setMenuOpen] = React.useState(false);

  const toggleMenu = () => setMenuOpen(!menuOpen);
  const handleMenuClose = () => setMenuOpen(false);

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (
        menuRef.current &&
        !menuRef.current.contains(event.target as Node) &&
        buttonRef.current &&
        !buttonRef.current.contains(event.target as Node)
      ) {
        setMenuOpen(false);
      }
    };

    if (menuOpen) {
      document.addEventListener('mousedown', handleClickOutside);
    }

    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, [menuOpen]);


  return (
    <header className="bg-gray-800 text-white">
      <div className="container mx-auto flex justify-between items-center px-4 py-3 relative">
        {/* Logo */}
        <Link href="/" className="text-2xl font-bold mx-auto md:mx-0">
          Job Portal
        </Link>

        {/* Hamburger */}
        <button
          ref={buttonRef}
          className="md:hidden focus:outline-none z-20 cursor-pointer"
          onClick={toggleMenu}
          aria-label="Toggle navigation"
        >
          <span className={`block w-6 h-0.5 bg-white mb-1 transition-transform ${menuOpen ? 'rotate-45 translate-y-2' : ''}`}></span>
          <span className={`block w-6 h-0.5 bg-white transition-opacity ${menuOpen ? 'opacity-0' : ''}`}></span>
          <span className={`block w-6 h-0.5 bg-white mt-1 transition-transform ${menuOpen ? '-rotate-45 -translate-y-2' : ''}`}></span>
        </button>

        {/* Menu */}
        <div
          ref={menuRef}
          className={`absolute ${menuOpen ? 'flex mt-[56px]' : 'hidden'} md:static bg-gray-800 top-0 left-0 w-full md:w-auto md:flex items-center z-10 transition-transform transform ${menuOpen ? 'translate-x-0' : '-translate-x-full'
            } md:translate-x-0`}
        >
          <ul className="flex flex-col md:flex-row md:space-x-6 mt-1 md:mt-0">
            <li>
              <Link href="/" className="block px-4 py-2 hover:bg-gray-700" onClick={handleMenuClose}>
                Home
              </Link>
            </li>

            {isLoggedIn && (
              <>
                <li>
                  <Link href="/post-job" className="block px-4 py-2 hover:bg-gray-700" onClick={handleMenuClose}>
                    Post a Job
                  </Link>
                </li>
                <li>
                  <Link href="/apply-job" className="block px-4 py-2 hover:bg-gray-700" onClick={handleMenuClose}>
                    Apply for Jobs
                  </Link>
                </li>
              </>
            )}

            {!isLoggedIn ? (
              <li>
                <Link href="/login" className="block px-4 py-2 hover:bg-gray-700" onClick={handleMenuClose}>
                  Login / Signup
                </Link>
              </li>
            ) : (
              <>
                <li>
                  <button
                    onClick={() => {
                      logout();
                      handleMenuClose();
                    }}
                    className="block px-4 py-2 hover:bg-gray-700 text-left w-full cursor-pointer"
                  >
                    Logout
                  </button>
                </li>

                {user?.email === 'singhsarabnoor@gmail.com' && (
                  <li>
                    <Link href="/admin" className="block px-4 py-2 hover:bg-gray-700" onClick={handleMenuClose}>
                      Admin
                    </Link>
                  </li>
                )}
              </>
            )}
          </ul>
        </div>
      </div>
    </header>
  );
};

export default Header;
