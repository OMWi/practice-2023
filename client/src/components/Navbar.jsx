import {
  AppBar,
  Container,
  Link,
  Stack,
  Toolbar,
  Typography,
} from "@mui/material";

export default function Navbar({ auth }) {
  return (
    <AppBar position="static">
      <Container maxWidth="lg">
        <Toolbar>
          <Stack direction="row" alignItems="center" spacing={1.5}>
            <Link href="/">
              <Typography variant="h6">Home</Typography>
            </Link>
            <Link href="/words">
              <Typography variant="h6">Words</Typography>
            </Link>
          </Stack>
          <Stack
            direction="row"
            flexGrow="1"
            justifyContent="flex-end"
            alignItems="center"
            spacing={1.5}
          >
            {auth && (
              <Link href="/profile">
                <Typography variant="h6">Profile</Typography>
              </Link>
            )}
            {!auth && (
              <>
                <Link href="/login">
                  <Typography variant="h6">Login</Typography>
                </Link>
                <Link href="/register">
                  <Typography variant="h6">Register</Typography>
                </Link>
              </>
            )}
          </Stack>
        </Toolbar>
      </Container>
    </AppBar>
  );
}
