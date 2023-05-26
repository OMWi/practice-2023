import {
  Avatar,
  Box,
  Container,
  Divider,
  List,
  ListItem,
  Paper,
  Stack,
  Table,
  Typography,
} from "@mui/material";
import { useLoaderData } from "react-router-dom";
import AuthService from "../services/auth";
import { stringToColor } from "../utils";
import UserDataService from "../services/user-data";

function UserData(id, name, exp, position) {
  return {
    id: id,
    name: name,
    exp: exp,
    position: position,
  };
}

export async function loader() {
  const userDataList = [
    UserData(1, "User Long Name", 10, 1),
    UserData(2, "Adam", 10, 2),
    UserData(3, "Some Random Color", 10, 3),
    UserData(4, "Pepega", 10, 4),
    UserData(5, "Okage", 10, 5),
    UserData(6, "Cool", 10, 6),
    UserData(7, "I dont know what to say", 10, 7),
    UserData(8, "White?", 10, 8),
    UserData(9, "Hope not", 10, 9),
  ];
  // const userDataList = UserDataService.getTop(100);

  return [userDataList, { userId: 2 }];
}

export default function LeaderBoard() {
  const [userDataList, user] = useLoaderData();

  return (
    <Container maxWidth="lg" sx={{ padding: 1 }}>
      <Paper>
        <Typography variant="h4" textAlign="center" sx={{ padding: 1 }}>
          Best Learners
        </Typography>
        <Divider />
        {userDataList.length > 0 && (
          <List>
            {userDataList.map((userData) => (
              <>
                <ListItem
                  key={userData.id}
                  sx={
                    user && user.userId === userData.id
                      ? { bgcolor: "selected.main" }
                      : {}
                  }
                >
                  <Stack width={1} spacing={0.5}>
                    <Stack
                      direction="row"
                      alignItems="center"
                      spacing={1}
                      width={1}
                    >
                      <Typography>{`${userData.position}.`}</Typography>
                      <Stack
                        direction="row"
                        justifyContent="left"
                        alignItems="center"
                        spacing={1}
                        sx={{ flexGrow: 1 }}
                      >
                        <Avatar sx={{ bgcolor: stringToColor(userData.name) }}>
                          {userData.name[0].toUpperCase()}
                        </Avatar>
                        <Typography noWrap>{userData.name}</Typography>
                      </Stack>
                      <Typography>{`${userData.exp} exp`}</Typography>
                    </Stack>
                  </Stack>
                </ListItem>
                <Divider />
              </>
            ))}
          </List>
        )}
      </Paper>
    </Container>
  );
}
